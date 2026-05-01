package api.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/product")
@MultipartConfig
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Map<String, String> map = new HashMap<>();
	
	public Product() {
		// 초기값(=default)
		map.put("강호동", "JSP");
		map.put("유재석", "HTML");
		map.put("신동엽", "JAVA");
		map.put("손흥민", "ORACLE");
		map.put("서장훈", "SPRING");
	}
	// 제품 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 필수
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name"); // DB 
		String language = map.get(name); // DB 
				
		//String name = "강호동";
		// 출력
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String json = """
				{
					"name" : "%s",
					"language" : "%s"
				}
				""".formatted(name, language); //jdk 15+
		
		
		out.print(json);
		out.flush();
		out.close();
		
		//System.out.println(map);
	}
	// 제품 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String language = request.getParameter("language");
		
		map.put(name, language);
			
		System.out.println(name + "이 추가되었습니다.");
				
	}

}
