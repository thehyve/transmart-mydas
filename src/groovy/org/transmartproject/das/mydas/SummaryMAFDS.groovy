package org.transmartproject.das.mydas

import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import transmart.mydas.VcfService
import uk.ac.ebi.mydas.configuration.DataSourceConfiguration
import uk.ac.ebi.mydas.configuration.PropertyType
import uk.ac.ebi.mydas.datasource.RangeHandlingAnnotationDataSource
import uk.ac.ebi.mydas.exceptions.BadReferenceObjectException
import uk.ac.ebi.mydas.exceptions.CoordinateErrorException
import uk.ac.ebi.mydas.exceptions.DataSourceException
import uk.ac.ebi.mydas.exceptions.UnimplementedFeatureException
import uk.ac.ebi.mydas.model.DasAnnotatedSegment
import uk.ac.ebi.mydas.model.DasEntryPoint
import uk.ac.ebi.mydas.model.DasType

import javax.servlet.ServletContext

/**
 * Created by rnugraha on 30-09-13.
 */
class SummaryMAFDS implements RangeHandlingAnnotationDataSource {

    VcfService vcfService
    List<DasEntryPoint> entryPoints

    @Override
    void init(ServletContext servletContext, Map<String, PropertyType> stringPropertyTypeMap, DataSourceConfiguration dataSourceConfiguration) throws DataSourceException {
        def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        this.vcfService = ctx.vcfService
    }

    @Override
    void destroy() {

    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, int start, int stop, Integer maxbins) throws BadReferenceObjectException, CoordinateErrorException, DataSourceException {
        return vcfService.getSummaryMAF([segmentId], maxbins, new uk.ac.ebi.mydas.model.Range(start, stop)).first()
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, int start, int stop, Integer maxbins, uk.ac.ebi.mydas.model.Range range) throws BadReferenceObjectException, CoordinateErrorException, DataSourceException, UnimplementedFeatureException {
        return vcfService.getSummaryMAF(resultInstanceId, [segmentId], maxbins, range).first()
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, Integer maxbins) throws BadReferenceObjectException, DataSourceException {
        return vcfService.getSummaryMAF([segmentId], maxbins).first()
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, Integer maxbins, uk.ac.ebi.mydas.model.Range range) throws BadReferenceObjectException, DataSourceException, UnimplementedFeatureException {
        return vcfService.getSummaryMAF([segmentId], maxbins, range).first()
    }

    @Override
    Collection<DasAnnotatedSegment> getFeatures(Collection<String> segmentIds, Integer maxbins) throws UnimplementedFeatureException, DataSourceException {
        return vcfService.getSummaryMAF(segmentIds, maxbins)
    }

    @Override
    Collection<DasAnnotatedSegment> getFeatures(Collection<String> segmentIds, Integer maxbins, uk.ac.ebi.mydas.model.Range range) throws UnimplementedFeatureException, DataSourceException {
        return vcfService.getSummaryMAF(segmentIds, maxbins, range)
    }


    @Override
    Collection<DasType> getTypes() throws DataSourceException {
        return null
    }


    @Override
    Integer getTotalCountForType(DasType dasType) throws DataSourceException {
        return null
    }

    @Override
    URL getLinkURL(String field, String id) throws UnimplementedFeatureException, DataSourceException {
        return null
    }

    @Override
    Collection<DasEntryPoint> getEntryPoints(Integer start, Integer stop) throws UnimplementedFeatureException, DataSourceException {
        return null
    }

    @Override
    String getEntryPointVersion() throws UnimplementedFeatureException, DataSourceException {
        return null
    }

    @Override
    int getTotalEntryPoints() throws UnimplementedFeatureException, DataSourceException {
        return 0
    }

}
