package transmart.mydas

import org.transmartproject.core.dataquery.DataQueryResource
import uk.ac.ebi.mydas.exceptions.UnimplementedFeatureException
import uk.ac.ebi.mydas.extendedmodel.DasMethodE
import uk.ac.ebi.mydas.model.DasAnnotatedSegment
import uk.ac.ebi.mydas.model.DasFeature
import uk.ac.ebi.mydas.model.DasFeatureOrientation
import uk.ac.ebi.mydas.model.DasPhase
import uk.ac.ebi.mydas.model.DasType
import uk.ac.ebi.mydas.model.Range
import org.transmartproject.core.dataquery.vcf.VcfValues

/**
 *
 * Created by rnugraha on 26-09-13.
 */
class VcfService extends AbstractTransmartDasService {

    //TODO Choose correct cvId(3-d parameter) from http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=SO
    private def vcfMethod = new DasMethodE('vcf', 'vcf', 'vcf-cv-id')
    String vcfVersion = '0.1'

    DataQueryResource dataQueryResourceNoGormService

    /**
     * Retrieve features
     * @return
     * @throws UnimplementedFeatureException
     */
    List<DasAnnotatedSegment> getCohortMAF(Long resultInstanceId, String conceptKey,
                                             Collection<String> segmentIds = [],
                                             Integer maxbins = null,
                                             Range range = null ) throws UnimplementedFeatureException {

        def query = createHighDimensionalQuery(resultInstanceId, conceptKey, segmentIds, range)
        def deVariantSubjectDetails =  dataQueryResourceNoGormService.getCohortMaf(query)
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails) { VcfValues val ->
            [id: val.rsId , type: 'maf', name: 'Cohort Minor Allele Frequency', score: val.maf]
        }
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    /**
     * Retrieve summary level of Minor Alele Frequency
     * @param segmentIds
     * @param maxbins
     * @param range
     * @return
     */
    List<DasAnnotatedSegment> getSummaryMAF(long resultInstanceId, String conceptKey, Collection<String> segmentIds = [],
                                            Integer maxbins = null,
                                            Range range = null) {
        def query = createHighDimensionalQuery(resultInstanceId, conceptKey, segmentIds, range)
        def deVariantSubjectDetails =  dataQueryResourceNoGormService.getSummaryMaf(query)
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails) { VcfValues val ->
            [id: val.rsId , type: 'smaf', name: 'Minor Allele Frequency', score: val.maf]
        }
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    List<DasAnnotatedSegment> getQualityByDepth(long resultInstanceId, String conceptKey, Collection<String> segmentIds = [],
                                            Integer maxbins = null,
                                            Range range = null) {
        def query = createHighDimensionalQuery(resultInstanceId, conceptKey, segmentIds, range)
        def deVariantSubjectDetails =  dataQueryResourceNoGormService.getSummaryMaf(query)
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails) { VcfValues val ->
            [id: val.rsId , type: 'qd', name: 'Quality of Depth', score: val.qualityOfDepth]
        }
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    List<DasAnnotatedSegment> getGenomicVariants(long resultInstanceId, String conceptKey,
                                                 Collection<String> segmentIds = [],
                                                 Integer maxbins = null,
                                                 Range range = null) {

        def query = createHighDimensionalQuery(resultInstanceId, conceptKey, segmentIds, range)
        def deVariantSubjectDetails =  dataQueryResourceNoGormService.getSummaryMaf(query)
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails) { VcfValues val ->
            [id: val.rsId , type: val.genomicVariantType, name: 'Genomic Variant Type', score: val.maf]
        }
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    private def constructSegmentFeaturesMap(List<VcfValues> deVariantSubjectDetails, Closure dataFetchClosure) {
        Map<String, List<DasFeature>> featuresPerSegment = [:]

        deVariantSubjectDetails.each {
            if (!featuresPerSegment[it.chromosome]) {
                featuresPerSegment[it.chromosome] = []
            }
            def data = dataFetchClosure(it)
            if(data.score > 0)
                featuresPerSegment[it.chromosome] << new DasFeature(
                        //FIXME This field could not be reused
                        // feature id - any unique id that represent this feature
                        "${data.type}-${data.id}",
                        // feature label
                        //FIXME This field could not be reused
                        data.name,
                        //FIXME This field could not be reused
                        // das type
                        new DasType("${data.type}", "", "", ""),
                        // das method TODO: pls find out what is actually means
                        vcfMethod,
                        // start pos
                        it.position.toInteger(),
                        // end pos
                        it.position.toInteger(),
                        // value - this is where Minor Allele Freq (MAF) value is placed
                        data.score,
                        // feature orientation  TODO: pls find out what is actually means
                        // lets put  DasFeatureOrientation.ORIENTATION_NOT_APPLICABLE by default
                        DasFeatureOrientation.ORIENTATION_NOT_APPLICABLE,
                        // phase TODO: pls find out what is actually means
                        // lets put DasPhase.PHASE_NOT_APPLICABLE by default
                        DasPhase.PHASE_NOT_APPLICABLE,
                        //notes
                        ["RefSNP=${it.rsId}",
                                "REF=${it.ref}",
                                "ALT=${it.alt}",
                                //TODO What names in vcf file
                                //"AlleleCount=",
                                "AlleleFrequency=${it.additionalInfo['AF'] ?: ''}",
                                //TODO What names in vcf file
                                //"TotalAllele=438",
                                //"BaseQRankSum=-9.563",
                                //"MQRankSum=2.462",
                                "dbSNPMembership=${it.additionalInfo['DB'] ?: 'No'}"]*.toString(),
                        //links
                        [(new URL("http://www.ncbi.nlm.nih.gov/projects/SNP/snp_ref.cgi?rs=${it.rsId}")): 'NCBI SNP Ref'],
                        //targets
                        [],
                        //parents
                        [],
                        //parts
                        []
                )
        }

        featuresPerSegment
    }

}
