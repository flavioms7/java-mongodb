package challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping("/recipe")
	public Recipe save(@RequestBody Recipe recipe) {
		return service.save(recipe);
	}

	@PutMapping("/recipe/{id}")
	public void update(@RequestBody Recipe recipe, @PathVariable String id) {
		service.update(id, recipe);
	}
 
	@DeleteMapping("/recipe/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@GetMapping("/recipe/{id}")
	public Recipe get(@PathVariable String id) {
		return service.get(id);
	}

	@GetMapping("/recipe/ingredient")
	public List<Recipe> listByIngredient(@RequestParam String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping("/recipe/search")
	public List<Recipe> search(@RequestParam String search) {
		return service.search(search);
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public void like(@PathVariable String id, @PathVariable String userId) {
		service.like(id, userId);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public void unlike(@PathVariable String id, @PathVariable String userId) {
		service.unlike(id, userId);
	}

	@PostMapping("/recipe/{id}/comment")
	public RecipeComment addComment(@RequestBody RecipeComment comment, @PathVariable String id) {
		return service.addComment(id, comment);
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public void updateComment(@RequestBody RecipeComment comment, @PathVariable String id, @PathVariable String commentId) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable String id, @PathVariable String commentId) {
		service.deleteComment(id, commentId);
	}
}