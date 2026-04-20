package com.darzalgames.darzalcommon.filehandling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

class DirectoryClearerTest {

	@Test
	void clear_onNonexistantDirectoryOrFile_doesNotThrow() {
		File file = new File("lalala/hooooo");

		assertDoesNotThrow(() -> DirectoryClearer.clear(file));
	}

	@Test
	void clear_onRealFile_deletesIt() throws IOException {
		File file = new File("temp.txt");
		Files.writeString(file.toPath(), "test file!");

		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		assertDoesNotThrow(() -> DirectoryClearer.clear(file));
		assertFalse(file.exists());
	}

	@Test
	void clear_onRealEmptyDirectory_deletesIt() throws IOException {
		File directory = new File("myFolder");
		Files.createDirectory(directory.toPath());

		assertTrue(directory.exists());
		assertTrue(directory.isDirectory());
		assertDoesNotThrow(() -> DirectoryClearer.clear(directory));
		assertFalse(directory.exists());
	}

	@Test
	void clear_onRealDirectoryWithFile_deletesBoth() throws IOException {
		File directory = new File("myFolder");
		Files.createDirectory(directory.toPath());
		File file = new File("myFolder/temp.txt");
		Files.writeString(file.toPath(), "test file, but nested!");

		assertTrue(directory.exists());
		assertTrue(directory.isDirectory());
		assertTrue(file.exists());
		assertFalse(file.isDirectory());
		assertDoesNotThrow(() -> DirectoryClearer.clear(directory));
		assertFalse(directory.exists());
		assertFalse(file.exists());
	}

}
