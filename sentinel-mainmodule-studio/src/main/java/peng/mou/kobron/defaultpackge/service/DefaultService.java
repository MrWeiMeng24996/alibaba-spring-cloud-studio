package peng.mou.kobron.defaultpackge.service;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DefaultService {
    public HttpServletResponse defaultMethod(HttpServletResponse response) throws IOException {

        System.out.println("This is defaultMethod........");
        InputStream inputStream = new FileInputStream("D:\\Users\\Peng\\Desktop\\tests.xlsx");
        EasyExcel.write(response.getOutputStream()).withTemplate(inputStream).sheet().doFill(new Object());
        return response;
    }
}
