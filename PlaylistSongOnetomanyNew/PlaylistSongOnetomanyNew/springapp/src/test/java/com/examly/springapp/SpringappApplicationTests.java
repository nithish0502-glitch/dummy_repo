package com.examly.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.persistence.OneToMany;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	void testAddPlaylist() throws Exception {
		String PlaylistJson = "{ \"playlistId\": 1, \"name\": \"Test Playlist\", \"description\": \"Test Song\", \"numofSongs\": 15 }";
		mockMvc.perform(MockMvcRequestBuilders.post("/playlist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(PlaylistJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.name").value("Test Playlist"))
				.andReturn();
	}

	@Test
	@Order(2)
	void testAddSongs() throws Exception {
		String songJson = "{ \"songId\": 1, \"title\": \"Test Title\", \"artist\": \"Test Artist\", \"movieName\": \"Test Movie\", \"composer\": \"Test Composer\" }";

		mockMvc.perform(MockMvcRequestBuilders.post("/song/1/playlist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(songJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.title").value("Test Title"))
				.andReturn();
	}

	@Test
	@Order(3)
	void testGetPlaylistsById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/playlist/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.playlistId").value(1))
				.andExpect(jsonPath("$.songs[?(@.artist == 'Test Artist')]").exists());
	}
	
	@Test
    @Order(4)
    public void testgetAllSongsByArtist() throws Exception {

		String artist ="Test Artist";
        mockMvc.perform(MockMvcRequestBuilders.get("/song/{artist}",artist)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
						"$[?(@.artist  == 'Test Artist')]")
                
                        .exists());
    }



  

@Test
@Order(5)
void testDeleteSong() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/song/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Song 1 deleted successfully"));
}

@Test
public void testMethodHasQueryAnnotation() {
        try {
            // Use reflection to get the Class object for the EmployeeRepo interface
            Class<?> repoClass = Class.forName("com.examly.springapp.repository.SongRepo");

            // Get all declared methods in the EmployeeRepo interface
            Method[] declaredMethods = repoClass.getDeclaredMethods();

            // Check each method for the @Query annotation
            boolean hasQueryAnnotation = false;
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Query.class)) {
                    hasQueryAnnotation = true;
                    break; // Stop checking once we find one method with @Query
                }
            }

            // If no method with @Query is found, fail the test
            if (!hasQueryAnnotation) {
                fail("No method with @Query annotation found in SongRepo interface.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }

@Test
    public void testOneToManyAnnotationPresent() {
        try {
            Class<?> playlistClass = Class.forName("com.examly.springapp.model.Playlist");
            Field songField = playlistClass.getDeclaredField("songs");
            OneToMany oneToManyAnnotation = songField.getAnnotation(OneToMany.class);

            assertNotNull(oneToManyAnnotation,"@OneToMany annotation should be present on 'playlist' field");
        } catch (ClassNotFoundException e) {
            fail("Playlist class not found");
        } catch (NoSuchFieldException e) {
            fail("Field 'songs' not found in Playlist class");
        }
    }





	@Test
	public void testControllerFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/controller";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testPlaylistControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/PlaylistController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testSongControllerFile() {
		String filePath = "src/main/java/com/examly/springapp/controller/SongController.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testModelFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/model";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testPlaylistModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Playlist.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testSongModelFile() {
		String filePath = "src/main/java/com/examly/springapp/model/Song.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testRepositoryFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/repository";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	public void testPlaylistRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/PlaylistRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testSongRepositoryFile() {
		String filePath = "src/main/java/com/examly/springapp/repository/SongRepo.java";
		// Replace with the path to your file
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

	@Test
	public void testServiceFolder() {
		String directoryPath = "src/main/java/com/examly/springapp/service";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test

	public void testPlaylistServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/PlaylistService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testSongServiceFile() {
		String filePath = "src/main/java/com/examly/springapp/service/SongService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testPlaylistServiceImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/PlaylistServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	public void testSongServiceImplFile() {
		String filePath = "src/main/java/com/examly/springapp/service/SongServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
	
}
