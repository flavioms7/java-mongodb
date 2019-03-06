package challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository repository;

	@Override
	public Recipe save(Recipe recipe) {
		return repository.save(recipe);
	}

	//title, description e ingredients
	@Override
	public void update(String id, Recipe recipe) {
		Recipe recipeAux = new Recipe();
		recipeAux = get(id);
		
		recipeAux.setTitle(recipe.getTitle());
		recipeAux.setDescription(recipe.getDescription());
		recipeAux.setIngredients(recipe.getIngredients());
		repository.save(recipeAux);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		Optional<Recipe> recipe = repository.findById(id);
		if(recipe.isPresent()) {
			return recipe.get();
		}
		return null;
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return repository.findByIngredientOrderByTitleAsc(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return null;
	}

	@Override
	public void like(String id, String userId) {
		Recipe x = repository.findById(id).get();
		x.likes.add(userId);
		repository.save(x);
	}
	@Override
	public void unlike(String id, String userId) {
		Recipe x = repository.findById(id).get();
		//x.likes.remove(x.getLikes().size()-1);
		for(int i=0; i<=x.getLikes().size()-1;i++) {
			if(x.getLikes().get(i).equals(userId)) {
				x.getLikes().remove(i);
				break;
			}
		}
		repository.save(x);
	}
	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		Recipe x = repository.findById(id).get();
		x.comments.add(comment);
		repository.save(x);
		return comment;
	}
	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Recipe x = repository.findById(id).get();
		for(int i=0; i<=x.getComments().size()-1;i++) {
			if(x.getComments().get(i).getId().equals(commentId)) {
				x.getComments().get(i).setComment(comment.getComment());;
				break;
			}
		}
		repository.save(x);
	}
	@Override
	public void deleteComment(String id, String commentId) {
		Recipe x = repository.findById(id).get();
		for(int i=0; i<=x.getComments().size()-1;i++) {
			if(x.getComments().get(i).getId().equals(commentId)) {
				x.getComments().remove(i);
				break;
			}
		}
		repository.save(x);
	}
}