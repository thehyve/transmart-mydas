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
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails)
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
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails)
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    List<DasAnnotatedSegment> getQualityByDepth(long resultInstanceId, String conceptKey, Collection<String> segmentIds = [],
                                            Integer maxbins = null,
                                            Range range = null) {
        def query = createHighDimensionalQuery(resultInstanceId, conceptKey, segmentIds, range)
        def deVariantSubjectDetails =  dataQueryResourceNoGormService.getSummaryMaf(query)
        def featuresPerSegment = constructSegmentFeaturesMap(deVariantSubjectDetails, [id: 'qd', name: 'Quality of Depth',scoreField: 'qualityOfDepth'])
        segmentIds.collect { new DasAnnotatedSegment(it, range?.getFrom(), range?.getTo(), vcfVersion, it, featuresPerSegment[it] ?: []) }
    }

    private def constructSegmentFeaturesMap(List<VcfValues> deVariantSubjectDetails, Map specificFields = [id: 'smaf', name: 'Minor Allele Frequency', scoreField: 'maf']) {
        Map<String, List<DasFeature>> featuresPerSegment = [:]

        deVariantSubjectDetails.each {
            if (!featuresPerSegment[it.chromosome]) {
                featuresPerSegment[it.chromosome] = []
            }
            Double score = it."${specificFields.scoreField}"
            if(score > 0)
                featuresPerSegment[it.chromosome] << new DasFeature(
                        //FIXME This field could not be reused
                        // feature id - any unique id that represent this feature
                        "${specificFields.id}-${it.rsId}",
                        // feature label
                        //FIXME This field could not be reused
                        specificFields.name,
                        //FIXME This field could not be reused
                        // das type
                        new DasType("${specificFields.id}", "", "", ""),
                        // das method TODO: pls find out what is actually means
                        vcfMethod,
                        // start pos
                        it.position.toInteger(),
                        // end pos
                        it.position.toInteger(),
                        // value - this is where Minor Allele Freq (MAF) value is placed
                        score,
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

    List<DasAnnotatedSegment> getGenomicVariants(long resultInstanceId, String conceptKey,
                                                 Collection<String> segmentIds = [],
                                                Integer maxbins = null,
                                                Range range = null) {

        // TODO to retrieve from backend

        // -----------------------------------------------
        // code below is only intended to serve dummy data
        // -----------------------------------------------

        // sample of URL
        def myURLs = [:]
        myURLs.put(new URL('http://www.thehyve.nl'), 'just dummy URL')

        // sample of expected genomic variant
        def dummyFeatures = [
                new DasFeature(
                        "gen-variants-1",
                        "gen-variants-1",
                        new DasType("SNP", "", "", ""),
                        vcfMethod,
                        30188050,
                        30188050,
                        null,
                        DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,
                        DasPhase.PHASE_NOT_APPLICABLE,
                        //notes
                        ["notes 1", "notes 2", "notes 3"],
                        //links
                        myURLs,
                        //targets
                        [],
                        //parents
                        [],
                        //parts
                        [])
                ,
                new DasFeature("gen-variants-2","gen-variants-2",new DasType("DIV", "", "", ""),vcfMethod,30188060,30188060,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-3","gen-variants-3",new DasType("SNP", "", "", ""),vcfMethod,30188062,30188062,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-4","gen-variants-4",new DasType("SNP", "", "", ""),vcfMethod,30188076,30188076,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-5","gen-variants-5",new DasType("SNP", "", "", ""),vcfMethod,30188080,30188080,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),

                new DasFeature("gen-variants-1","gen-variants-1",new DasType("DEL", "", "", ""),vcfMethod,30188040,30188040,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-2","gen-variants-2",new DasType("INS", "", "", ""),vcfMethod,30189100,30189100,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-3","gen-variants-3",new DasType("DEL", "", "", ""),vcfMethod,30189120,30189120,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-4","gen-variants-4",new DasType("DEL", "", "", ""),vcfMethod,30189150,30189150,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-5","gen-variants-5",new DasType("INS", "", "", ""),vcfMethod,30189188,30189188,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-6","gen-variants-6",new DasType("DEL", "", "", ""),vcfMethod,30189112,30189112,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-7","gen-variants-7",new DasType("DEL", "", "", ""),vcfMethod,30189134,30189134,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-8","gen-variants-8",new DasType("DEL", "", "", ""),vcfMethod,30189114,30189114,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-9","gen-variants-9",new DasType("INS", "", "", ""),vcfMethod,30189199,30189199,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-10","gen-variants-10",new DasType("INS", "", "", ""),vcfMethod,30189145,30189145,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-11","gen-variants-11",new DasType("INS", "", "", ""),vcfMethod,30189142,30189142,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-12","gen-variants-12",new DasType("DEL", "", "", ""),vcfMethod,30189176,30189176,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-13","gen-variants-13",new DasType("INS", "", "", ""),vcfMethod,30189182,30189182,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-14","gen-variants-14",new DasType("INS", "", "", ""),vcfMethod,30189200,30189200,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-15","gen-variants-15",new DasType("INS", "", "", ""),vcfMethod,30189243,30189243,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-16","gen-variants-16",new DasType("INS", "", "", ""),vcfMethod,30189287,30189287,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-17","gen-variants-17",new DasType("DEL", "", "", ""),vcfMethod,30189278,30189278,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-18","gen-variants-18",new DasType("DEL", "", "", ""),vcfMethod,30189213,30189213,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-19","gen-variants-19",new DasType("INS", "", "", ""),vcfMethod,30189254,30189254,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-20","gen-variants-20",new DasType("INS", "", "", ""),vcfMethod,30189253,30189253,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-21","gen-variants-21",new DasType("INS", "", "", ""),vcfMethod,30189224,30189224,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-22","gen-variants-22",new DasType("DEL", "", "", ""),vcfMethod,30189210,30189210,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-23","gen-variants-23",new DasType("INS", "", "", ""),vcfMethod,30189299,30189299,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-24","gen-variants-24",new DasType("INS", "", "", ""),vcfMethod,30189265,30189265,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("gen-variants-25","gen-variants-25",new DasType("DEL", "", "", ""),vcfMethod,30189262,30189262,null,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
        ]

        [new DasAnnotatedSegment('22' , 30109479 , 30352561 , 'dummy.version', 'label for segment 22', dummyFeatures)]

    }

}
