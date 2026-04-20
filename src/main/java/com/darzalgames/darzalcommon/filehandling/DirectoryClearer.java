package com.darzalgames.darzalcommon.filehandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

public class DirectoryClearer {

	private DirectoryClearer() {
		// utility class
	}

	/**
	 * @param file the root directory to clear recursively
	 * @throws IOException thrown if the deletion failed
	 */
	public static void clear(File file) throws IOException {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				clear(f);
			}
		}
		try {
			Files.delete(file.toPath());
		} catch (NoSuchFileException e) {
			System.err.println("Attempting to delete a non-existent file: " + file.getPath());
		}
	}

}
