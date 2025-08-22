package com.jusvid.JusVid.controller;

import com.jusvid.JusVid.model.Person;
import com.jusvid.JusVid.model.Video;
import com.jusvid.JusVid.repository.VideoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class VideoController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/video-upload")
    public String showUploadPage() {
        return "video-upload";
    }
    @GetMapping("/upload")
    public String redirectToUploadPage() {
        return "video-upload";
    }


    @PostMapping("/upload")
    public String handleVideoUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    HttpSession session,
                                    Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "video-upload";
        }

        // ✅ Check extension (only .mp4 allowed)
        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".mp4")) {
            model.addAttribute("message", "Only MP4 files are allowed.");
            return "video-upload";
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setFilePath(UPLOAD_DIR + fileName);

            Person user = (Person) session.getAttribute("loggedInPerson");
            if (user != null) {
                video.setUploadedBy(user);
            }

            videoRepository.save(video);
            model.addAttribute("message", "Video uploaded successfully!");

        } catch (IOException e) {
            model.addAttribute("message", "Video upload failed.");
        }

        return "video-upload";
    }


    @GetMapping(value={"","/","/watch"})
    public String viewAllVideos(Model model) {
        List<Video> videos = videoRepository.findAll();
        model.addAttribute("videos", videos);
        return "watch";
    }
    @GetMapping("/admin-videos")
    public String adminVideos(Model model) {
        List<Video> videos = videoRepository.findAll();
        model.addAttribute("videos", videos);
        return "admin-videos";
    }
    @GetMapping("/user/my-videos")
    public String myVideos(HttpSession session, Model model) {
        Person user = (Person) session.getAttribute("loggedInPerson");
        List<Video> videos = videoRepository.findByUploadedBy(user);
        model.addAttribute("videos", videos);
        return "my-videos";
    }



    // For USER - Deletes only their own videos
    @PostMapping("/delete-my-video")
    public String deleteMyVideo(@RequestParam("videoId") int videoId, HttpSession session) {
        Person user = (Person) session.getAttribute("loggedInPerson");
        if (user == null) return "redirect:/login";

        Video video = videoRepository.findById(videoId).orElse(null);
        if (video == null || video.getUploadedBy() == null ||
                user.getPersonId() != video.getUploadedBy().getPersonId()) {
            return "redirect:/user/my-videos";
        }



        deleteVideoFile(video.getFilePath());
        videoRepository.deleteById(videoId);

        return "redirect:/user/my-videos";
    }


    // For ADMIN - Deletes any video
    @PostMapping("/admin/delete-video")
    public String deleteAdminVideo(@RequestParam("videoId") int videoId) {
        Video video = videoRepository.findById(videoId).orElse(null);
        if (video != null) {
            deleteVideoFile(video.getFilePath());
            videoRepository.deleteById(videoId);
        }
        return "redirect:/admin-videos";
    }

    // File Deletion Helper
    private void deleteVideoFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        } catch (IOException ignored) {}
    }


}
