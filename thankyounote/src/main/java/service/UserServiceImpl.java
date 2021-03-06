package service;

import dao.UserDao;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Autowired
    HttpSession session;

    public String loginCheck(Map<String, String> map) {

        System.out.println("UserServiceImpl 접속 >>>>>>>>>>>>>>");

        UserDto userDto = null;

        userDto = userDao.loginCheck(map);

        if(userDto == null) {
            return "non_exist";
        } else {
            session.setAttribute("id", map.get("id"));
            session.setAttribute("name", userDto.getName());

            return "exist";
        }
    }

    @Override
    public void register(Map<String, String> map) {
        userDao.register(map);
    }

    @Override
    public void logout() {
        //세션 종료
        session.invalidate();
    }

    @Override
    public String valCheck(String id) {

        UserDto userDto = null;

        userDto = userDao.valCheck(id);

        System.out.println("userDTO : " + userDto);

        if(userDto == null) {
            return "non_exist";
        } else {

            return "exist";
        }

    }
}
