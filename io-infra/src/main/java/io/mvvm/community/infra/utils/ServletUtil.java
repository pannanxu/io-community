package io.mvvm.community.infra.utils;

import io.mvvm.community.infra.model.result.AppResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author: Pan
 **/
@Slf4j
public class ServletUtil {

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static Integer getParamToInt(String name) {
        return Convert.parseInteger(getParameter(name));
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 获取远程客户端IP地址
     *
     * @return IP
     */
    public static String getRemoteAddr() {
        return getRequest().getRemoteAddr();
    }

    public static void renderJson(HttpServletResponse response, String json) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void renderHtml(HttpServletResponse response, String json) {
        try {
            response.setStatus(200);
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResponseEntity<Object> buildJsonResponseEntity(AppResult result) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(Json.toJsonString(result), httpHeaders, HttpStatus.OK);
    }

    public static String getRequestBody() {
        HttpServletRequest request = getRequest();
        return getRequestBody(request);
    }

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder  sb     = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("Servlet|获取请求参数异常|{}", e.getMessage(), e);
        } finally {
            if (reader != null) {
                IOUtils.closeQuietly(reader);
            }
        }
        return sb.toString();
    }

    public static void addHeader(String name, String value) {
        getResponse().addHeader(name, safeHttpHeader(value));
    }

    /**
     * 下载文件
     */
    public static void downloadFile(String fileName, InputStream in) {
        downloadFile(getRequest(), getResponse(), fileName, in);
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName, InputStream in) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", safeHttpHeader("attachment; filename=" + fileName));
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("文件下载|异常|{}", e.getMessage(), e);
        } finally {
            if (null != in) {
                IOUtils.closeQuietly(in);
            }
        }
    }

    /**
     * CRLF fix.
     */
    private static String safeHttpHeader(String value) {
        return StringUtils.trimToEmpty(value)
                .replace("\r", "")
                .replace("\n", "")
                .replace("\r\n", "")
                .replace("\t", "")
                .replace(" ", "")
                .replace("'", "")
                ;
    }

}