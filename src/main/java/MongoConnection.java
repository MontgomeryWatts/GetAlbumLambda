import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoConnection {
    private MongoCollection<Document> artistCollection;

    public MongoConnection(){
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGODB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        artistCollection = db.getCollection("albums");
    }

    public Document getAlbumDocument(String albumId){
        return artistCollection.find(Filters.eq("_id", albumId)).first();
    }
}