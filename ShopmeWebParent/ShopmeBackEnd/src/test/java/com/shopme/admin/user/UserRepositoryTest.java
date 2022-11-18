package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user1 = new User("mail@mail.com","pass","First Name","Last Name");
		user1.addRole(roleAdmin);

		User savedUser = repo.save(user1);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		Role editor = new Role(3);
		Role roleAssistant = new Role(5);
		User user2 = new User("user2@mail.com", "pass2", "First2 Name2", "Last2 Name2");
		user2.addRole(editor);
		user2.addRole(roleAssistant);

		User savedUser = repo.save(user2);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User userId = repo.findById(1).get();
		System.out.println(userId);
		assertThat(userId).isNotNull();
	}
		
	@Test
	public void testUpdateUserDetails() {
		User userId = repo.findById(1).get();
		userId.setEnabled(true);
		userId.setEmail("updated@mail.com");

		repo.save(userId);
	}
		
	@Test
	public void testUpdateUserRole(){
		User userId = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesPerson = new Role(2);
		
		userId.getRoles().remove(roleEditor);
		userId.addRole(roleSalesPerson);
		
		repo.save(userId);
	}
	
	@Test
	public void testDeleteUSer() {
		//deleting second user with id=2
		repo.deleteById(2);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "'mail@mail.to'";
		User user = repo.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById(){
		Integer id = 1;
		Long countByID = repo.countById(id);
		assertThat(countByID).isNotNull().isGreaterThan(0);
	}
}
