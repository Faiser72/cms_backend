package com.vetologic.cms.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public class FileUploader {

	public boolean uploadDoctorFiles(MultipartFile file, String subFolderName, int id) {
		try {
			if ((file != null) && (!file.isEmpty())) {
				byte[] bytes = file.getBytes();

				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				File dir = new File(rootPath + File.separator + "Uploads" + File.separator + "doctors" + File.separator
						+ File.separator + subFolderName);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + id + "_" + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean uploadThyroidFiles(MultipartFile file, String subFolderName, int id) {
		try {
			if ((file != null) && (!file.isEmpty())) {
				byte[] bytes = file.getBytes();

				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				File dir = new File(rootPath + File.separator + "Uploads" + File.separator + "patient" + File.separator
						+ File.separator + subFolderName);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + id + "_" + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean uploadTestReportFiles(MultipartFile file, String subFolderName, int id) {
		try {
			if ((file != null) && (!file.isEmpty())) {
				byte[] bytes = file.getBytes();

				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				File dir = new File(rootPath + File.separator + "Uploads" + File.separator + "patient" + File.separator
						+ subFolderName);

				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + id + "_" + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Resource loadFileAsResource(String fileName, String subFolderName) throws FileNotFoundException {
		try {
			Path rootPath = Paths.get("uploads" + File.separator + "patient" + File.separator + subFolderName)
					.toAbsolutePath().normalize();
			Path filePath = rootPath.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File Not Found " + fileName);
			}
		} catch (Exception e) {
			throw new FileNotFoundException("File Not Found " + fileName);
		}
	}
}
