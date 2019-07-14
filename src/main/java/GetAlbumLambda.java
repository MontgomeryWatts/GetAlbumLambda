import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.bson.Document;

import java.util.Map;

public class GetAlbumLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final MongoConnection mongo;

    static {
        mongo = new MongoConnection();
    }

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);

        Map<String, String> map = event.getPathParameters();
        String albumId = map.get("albumId");
        if (albumId != null) {
            Document albumDoc = mongo.getAlbumDocument(albumId);
            if (albumDoc != null) response.setBody(albumDoc.toJson());
        }
        return response;
    }
}