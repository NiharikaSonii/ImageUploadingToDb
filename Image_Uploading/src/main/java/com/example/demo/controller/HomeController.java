package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojo.Images;
import com.example.demo.repo.ImageRepo;

@Controller
public class HomeController {

	@Autowired
	private ImageRepo imgrepo;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/imageUpload")
	public String imageUpload(@RequestParam MultipartFile img,HttpSession session) {
		Images im=new Images();
		im.setImageName(img.getOriginalFilename());
		Images uploadImg=imgrepo.save(im);
		
		if(uploadImg!=null) {
			try {
				File saveFile=new ClassPathResource("static/img").getFile();
				Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+img.getOriginalFilename());
				Files.copy(img.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("msg","Image Uploaded sucessfully");
		return "redirect:/";
	}
	
	
	
}
