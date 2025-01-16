package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Устанавливаем тип контента
        resp.setContentType("text/html;charset=UTF-8");

        //Получаем из запроса
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        boolean isAddUser = UserRepository.addUser(username, password, confirmPassword);

        //Готовим ответ
        if (isAddUser) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            req.getRequestDispatcher("/login.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/registration.html").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        if (username == null) {
            req.getRequestDispatcher("/registration.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/page/todo-list.html").forward(req, resp);
        }
    }
}
