// [LICENCE-HEADER]
//
package vn.pyco.tinycms.web.pages.admin.users;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;

import vn.pyco.tinycms.model.User;
import vn.pyco.tinycms.services.UserService;
import vn.pyco.tinycms.web.base.AdminPage;

/**
 * <Briefly describing the purpose of the class/interface...>
 * 
 */
public class EditProfile extends AdminPage {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService _userService;

    @Persist
    private User _user;

    public User getUser() {
        return _user;
    }

    public void setUser(User user) {
        _user = user;
    }

    public EditProfile() {
    }

    @BeginRender
    void beginRender() {
        String currentUserName = _userService.getCurrentUser().getUsername();
        _user = _userService.getUserByUsername(currentUserName);
    }

    @OnEvent(value = EventConstants.SUCCESS)
    Object submitForm() {
        _userService.saveUser(_user);
        return vn.pyco.tinycms.web.pages.admin.Index.class;
    }
}
