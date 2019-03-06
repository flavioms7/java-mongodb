package challenge;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
	
	@Query("{ 'ingredients' : ?0 }")
    List<Recipe> findByIngredientOrderByTitleAsc(String ingredient);
		
}
