package transmart.mydas

import uk.ac.ebi.mydas.exceptions.UnimplementedFeatureException
import uk.ac.ebi.mydas.extendedmodel.DasMethodE
import uk.ac.ebi.mydas.model.DasAnnotatedSegment
import uk.ac.ebi.mydas.model.DasFeature
import uk.ac.ebi.mydas.model.DasFeatureOrientation
import uk.ac.ebi.mydas.model.DasPhase
import uk.ac.ebi.mydas.model.DasTarget
import uk.ac.ebi.mydas.model.DasType
import uk.ac.ebi.mydas.model.Range

/**
 *
 * Created by rnugraha on 26-09-13.
 */
class VcfService {

    //TODO Choose correct cvId(3-d parameter) from http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=SO
    private def vcfMethod = new DasMethodE('vcf', 'vcf label', 'vcf-cv-id')

    /**
     * Retrieve features
     * @return
     * @throws UnimplementedFeatureException
     */
    List<DasAnnotatedSegment> getCohortMAF(Long resultInstanceId,
                                             Collection<String> segmentIds = [],
                                             Integer maxbins = null,
                                             Range range = null ) throws UnimplementedFeatureException {

        // TODO to retrieve from backend

        // -----------------------------------------------
        // code below is only intended to serve dummy data
        // -----------------------------------------------

        def dummyFeatures = [
                new DasFeature("sample-maf-1","sample-maf-1",new DasType("maf", "", "", ""),vcfMethod,30188040,30188040,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-2","sample-maf-2",new DasType("maf", "", "", ""),vcfMethod,30189100,30189100,0.6,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-3","sample-maf-3",new DasType("maf", "", "", ""),vcfMethod,30189120,30189120,0.1,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-4","sample-maf-4",new DasType("maf", "", "", ""),vcfMethod,30189150,30189150,0.45,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-5","sample-maf-5",new DasType("maf", "", "", ""),vcfMethod,30189188,30189188,0.8,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-6","sample-maf-6",new DasType("maf", "", "", ""),vcfMethod,30189112,30189112,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-7","sample-maf-7",new DasType("maf", "", "", ""),vcfMethod,30189134,30189134,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-8","sample-maf-8",new DasType("maf", "", "", ""),vcfMethod,30189114,30189114,0.9,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-9","sample-maf-9",new DasType("maf", "", "", ""),vcfMethod,30189199,30189199,0.4,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-10","sample-maf-10",new DasType("maf", "", "", ""),vcfMethod,30189145,30189145,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-11","sample-maf-11",new DasType("maf", "", "", ""),vcfMethod,30189142,30189142,0.5,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-12","sample-maf-12",new DasType("maf", "", "", ""),vcfMethod,30189176,30189176,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-13","sample-maf-13",new DasType("maf", "", "", ""),vcfMethod,30189182,30189182,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-14","sample-maf-14",new DasType("maf", "", "", ""),vcfMethod,30189200,30189200,0.6,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-15","sample-maf-15",new DasType("maf", "", "", ""),vcfMethod,30189243,30189243,0.4,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-16","sample-maf-16",new DasType("maf", "", "", ""),vcfMethod,30189287,30189287,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-17","sample-maf-17",new DasType("maf", "", "", ""),vcfMethod,30189278,30189278,0.6,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-18","sample-maf-18",new DasType("maf", "", "", ""),vcfMethod,30189213,30189213,0.1,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-19","sample-maf-19",new DasType("maf", "", "", ""),vcfMethod,30189254,30189254,0.5,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-20","sample-maf-20",new DasType("maf", "", "", ""),vcfMethod,30189253,30189253,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-21","sample-maf-21",new DasType("maf", "", "", ""),vcfMethod,30189224,30189224,0.5,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-22","sample-maf-22",new DasType("maf", "", "", ""),vcfMethod,30189210,30189210,0.9,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-23","sample-maf-23",new DasType("maf", "", "", ""),vcfMethod,30189299,30189299,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-24","sample-maf-24",new DasType("maf", "", "", ""),vcfMethod,30189265,30189265,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("sample-maf-25","sample-maf-25",new DasType("maf", "", "", ""),vcfMethod,30189262,30189262,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),

        ]

        [new DasAnnotatedSegment('22' , 30109479 , 30352561 , 'dummy.version', 'label for segment 22', dummyFeatures)]
    }

    /**
     * Retrieve summary level of Minor Alele Frequency
     * @param segmentIds
     * @param maxbins
     * @param range
     * @return
     */
    List<DasAnnotatedSegment> getSummaryMAF(Collection<String> segmentIds = [],
                                            Integer maxbins = null,
                                            Range range = null) {

        // TODO to retrieve from backend

        // -----------------------------------------------
        // code below is only intended to serve dummy data
        // -----------------------------------------------

        def dummyFeatures = [
                new DasFeature("summary-maf-1","summary-maf-1",new DasType("smaf", "", "", ""),vcfMethod,30188040,30188040,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-2","summary-maf-2",new DasType("smaf", "", "", ""),vcfMethod,30189100,30189100,0.6,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-3","summary-maf-3",new DasType("smaf", "", "", ""),vcfMethod,30189120,30189120,0.1,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-4","summary-maf-4",new DasType("smaf", "", "", ""),vcfMethod,30189150,30189150,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-5","summary-maf-5",new DasType("smaf", "", "", ""),vcfMethod,30189188,30189188,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-6","summary-maf-6",new DasType("smaf", "", "", ""),vcfMethod,30189112,30189112,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-7","summary-maf-7",new DasType("smaf", "", "", ""),vcfMethod,30189134,30189134,0.24,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-8","summary-maf-8",new DasType("smaf", "", "", ""),vcfMethod,30189114,30189114,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-9","summary-maf-9",new DasType("smaf", "", "", ""),vcfMethod,30189199,30189199,0.4,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-10","summary-maf-10",new DasType("smaf", "", "", ""),vcfMethod,30189145,30189145,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-11","summary-maf-11",new DasType("smaf", "", "", ""),vcfMethod,30189142,30189142,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-12","summary-maf-12",new DasType("smaf", "", "", ""),vcfMethod,30189176,30189176,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-13","summary-maf-13",new DasType("smaf", "", "", ""),vcfMethod,30189182,30189182,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-14","summary-maf-14",new DasType("smaf", "", "", ""),vcfMethod,30189200,30189200,0.6,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-15","summary-maf-15",new DasType("smaf", "", "", ""),vcfMethod,30189243,30189243,0.9,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-16","summary-maf-16",new DasType("smaf", "", "", ""),vcfMethod,30189287,30189287,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-17","summary-maf-17",new DasType("smaf", "", "", ""),vcfMethod,30189278,30189278,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-18","summary-maf-18",new DasType("smaf", "", "", ""),vcfMethod,30189213,30189213,0.1,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-19","summary-maf-19",new DasType("smaf", "", "", ""),vcfMethod,30189254,30189254,0.7,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-20","summary-maf-20",new DasType("smaf", "", "", ""),vcfMethod,30189253,30189253,0.2,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-21","summary-maf-21",new DasType("smaf", "", "", ""),vcfMethod,30189224,30189224,0.5,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-22","summary-maf-22",new DasType("smaf", "", "", ""),vcfMethod,30189210,30189210,0.5,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-23","summary-maf-23",new DasType("smaf", "", "", ""),vcfMethod,30189299,30189299,0.3,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-24","summary-maf-24",new DasType("smaf", "", "", ""),vcfMethod,30189265,30189265,0.9,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("summary-maf-25","summary-maf-25",new DasType("smaf", "", "", ""),vcfMethod,30189262,30189262,0.8,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),

        ]

        [new DasAnnotatedSegment('22' , 30109479 , 30352561 , 'dummy.version', 'label for segment 22', dummyFeatures)]

    }

    List<DasAnnotatedSegment> getQualityByDepth(Collection<String> segmentIds = [],
                                            Integer maxbins = null,
                                            Range range = null) {

        // TODO to retrieve from backend

        // -----------------------------------------------
        // code below is only intended to serve dummy data
        // -----------------------------------------------

        def dummyFeatures = [
                new DasFeature("quality-depth-1","quality-depth-1",new DasType("qd", "", "", ""),vcfMethod,30188040,30188040,24,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-2","quality-depth-2",new DasType("qd", "", "", ""),vcfMethod,30189100,30189100,15,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-3","quality-depth-3",new DasType("qd", "", "", ""),vcfMethod,30189120,30189120,23,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-4","quality-depth-4",new DasType("qd", "", "", ""),vcfMethod,30189150,30189150,35,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-5","quality-depth-5",new DasType("qd", "", "", ""),vcfMethod,30189188,30189188,22,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-6","quality-depth-6",new DasType("qd", "", "", ""),vcfMethod,30189112,30189112,11,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-7","quality-depth-7",new DasType("qd", "", "", ""),vcfMethod,30189134,30189134,56,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-8","quality-depth-8",new DasType("qd", "", "", ""),vcfMethod,30189114,30189114,34,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-9","quality-depth-9",new DasType("qd", "", "", ""),vcfMethod,30189199,30189199,12,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-10","quality-depth-10",new DasType("qd", "", "", ""),vcfMethod,30189145,30189145,72,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-11","quality-depth-11",new DasType("qd", "", "", ""),vcfMethod,30189142,30189142,13,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-12","quality-depth-12",new DasType("qd", "", "", ""),vcfMethod,30189176,30189176,35,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-13","quality-depth-13",new DasType("qd", "", "", ""),vcfMethod,30189182,30189182,44,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-14","quality-depth-14",new DasType("qd", "", "", ""),vcfMethod,30189200,30189200,21,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-15","quality-depth-15",new DasType("qd", "", "", ""),vcfMethod,30189243,30189243,76,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-16","quality-depth-16",new DasType("qd", "", "", ""),vcfMethod,30189287,30189287,35,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-17","quality-depth-17",new DasType("qd", "", "", ""),vcfMethod,30189278,30189278,64,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-18","quality-depth-18",new DasType("qd", "", "", ""),vcfMethod,30189213,30189213,65,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-19","quality-depth-19",new DasType("qd", "", "", ""),vcfMethod,30189254,30189254,32,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-20","quality-depth-20",new DasType("qd", "", "", ""),vcfMethod,30189253,30189253,23,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-21","quality-depth-21",new DasType("qd", "", "", ""),vcfMethod,30189224,30189224,55,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-22","quality-depth-22",new DasType("qd", "", "", ""),vcfMethod,30189210,30189210,53,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-23","quality-depth-23",new DasType("qd", "", "", ""),vcfMethod,30189299,30189299,34,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-24","quality-depth-24",new DasType("qd", "", "", ""),vcfMethod,30189265,30189265,56,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),
                new DasFeature("quality-depth-25","quality-depth-25",new DasType("qd", "", "", ""),vcfMethod,30189262,30189262,87,DasFeatureOrientation.ORIENTATION_ANTISENSE_STRAND,DasPhase.PHASE_NOT_APPLICABLE,[],[:],[],[],[]),

        ]

        [new DasAnnotatedSegment('22' , 30109479 , 30352561 , 'dummy.version', 'label for segment 22', dummyFeatures)]

    }


    List<DasAnnotatedSegment> getGenomicVariants(Collection<String> segmentIds = [],
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
