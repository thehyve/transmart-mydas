package org.transmartproject.das.mydas

import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import transmart.mydas.AcghService
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
import uk.ac.ebi.mydas.model.Range

import javax.servlet.ServletContext

/**
 * Created with IntelliJ IDEA.
 * User: Ruslan Forostianov
 * Date: 31/07/2013
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
class AcghDS implements RangeHandlingAnnotationDataSource {

    AcghService acghService
    Long resultInstanceId
    String conceptKey

    List<DasEntryPoint> entryPoints

    @Override
    void init(ServletContext servletContext, Map<String, PropertyType> stringPropertyTypeMap, DataSourceConfiguration dataSourceConfiguration) throws DataSourceException {
        // getting result instant id
        resultInstanceId = dataSourceConfiguration.getMatcherAgainstDsn().group(1).toLong()
        def ckEncoded = dataSourceConfiguration.getMatcherAgainstDsn().group(2)
        if (ckEncoded) {
            //TODO Double encoding/decoding because we have problem with single
            // encoded concept key (400 Bad Request) in earlier stages (possibly mydas)
            ckEncoded = URLDecoder.decode(ckEncoded, 'UTF-8')
            conceptKey = URLDecoder.decode(ckEncoded, 'UTF-8')
        }
        // get servlet context
        def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        // get service
        this.acghService = ctx.acghService
    }

    @Override
    void destroy() {}

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, Integer maxbins) throws BadReferenceObjectException, DataSourceException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, [segmentId], maxbins).first()
    }

    @Override
    Collection<DasAnnotatedSegment> getFeatures(Collection<String> segmentIds, Integer maxbins) throws UnimplementedFeatureException, DataSourceException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, segmentIds, maxbins)
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, Integer maxbins, uk.ac.ebi.mydas.model.Range range) throws BadReferenceObjectException, DataSourceException, UnimplementedFeatureException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, [segmentId], maxbins, range).first()
    }

    @Override
    Collection<DasAnnotatedSegment> getFeatures(Collection<String> segmentIds, Integer maxbins, uk.ac.ebi.mydas.model.Range range) throws UnimplementedFeatureException, DataSourceException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, segmentIds, maxbins, range)
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, int start, int stop, Integer maxbins) throws BadReferenceObjectException, CoordinateErrorException, DataSourceException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, [segmentId], maxbins, new uk.ac.ebi.mydas.model.Range(start, stop)).first()
    }

    @Override
    DasAnnotatedSegment getFeatures(String segmentId, int start, int stop, Integer maxbins, Range rows) throws BadReferenceObjectException, CoordinateErrorException, DataSourceException, UnimplementedFeatureException {
        acghService.getAcghFeatures(resultInstanceId, conceptKey, [segmentId], maxbins, rows).first()
    }

    @Override
    Collection<DasType> getTypes() throws DataSourceException {
        acghService.acghDasTypes
    }

    //Optional
    @Override
    Integer getTotalCountForType(DasType dasType) throws DataSourceException { null }

    //Optional
    @Override
    URL getLinkURL(String field, String id) throws UnimplementedFeatureException, DataSourceException { null }

    @Override
    Collection<DasEntryPoint> getEntryPoints(Integer start, Integer stop) throws UnimplementedFeatureException, DataSourceException {
        getEntryPoints()
    }

    @Override
    String getEntryPointVersion() throws UnimplementedFeatureException, DataSourceException {
        acghService.acghEntryPointVersion
    }

    @Override
    int getTotalEntryPoints() throws UnimplementedFeatureException, DataSourceException {
        getEntryPoints().size()
    }

    List<DasEntryPoint> getEntryPoints() {
        if (entryPoints == null) {
            entryPoints = acghService.getAcghEntryPoints(resultInstanceId)
        }
        entryPoints
    }

}
