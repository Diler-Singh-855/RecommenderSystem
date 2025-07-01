import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import java.io.File;
import java.util.List;

public class main {
    public static void main(String[] args) {
        try {
            // Load dataset
            DataModel model = new FileDataModel(new File("data/ratings.csv"));

            // Similarity algorithm
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Find nearest 2 users
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Create recommender
            GenericUserBasedRecommender recommender =
                    new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Get recommendations for user with ID 1
            List<RecommendedItem> recommendations = recommender.recommend(1, 2);

            // Print recommended items
            for (RecommendedItem item : recommendations) {
                System.out.println("Recommended Item ID: " + item.getItemID() + ", Score: " + item.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
