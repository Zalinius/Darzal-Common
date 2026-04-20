package com.darzalgames.darzalcommon.filehandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

/**
 * Helper class for deleting files/directories
 */
public class DirectoryClearer {

	private DirectoryClearer() {
		// utility class
	}

	/**
	 * Recursively clears and deletes files. Specifying a non-existent file is allowed, but achieves nothing.
	 * @param file a particular file to delete, or the root directory to begin clearing from
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
