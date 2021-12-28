package cn.edu.jsu.zjj.running.upload;

import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping
    public Result uploadFile(MultipartFile uploadFIle, String url) throws IOException {
        UploadFile uploadFile=new UploadFile();
        url = uploadFile.uploadFile(uploadFIle, url);
        return Result.success("上传成功",url);
    }
}
