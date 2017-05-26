<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/myform_1_0.tld" prefix="form" %>

<html>
<div id="mainPaneset" style="padding:20px">
    <div id="scrollingcontent" style="overflow: auto;">
        <div class="innerContent">
            <!--start content area-->
            <h2 style="display:inline;">
                <div id="logo">"dialogTitleVersion" nlsid="MSG_VERSION"/></div>
            </h2>
            <!--start content area-->
            <table align='center' cellspacing='0' cellpadding='0' border='0'>
                <tr>
                    <td><form:requiredfieldvalidator name='Login.CONTROL_USERNAME_VALIDATOR'
                                                    controltovalidate='Login.CONTROL_USERNAME'
                                                    nlsid='Login.MSG_USERNAME_REQUIRED' indicator=""/></td>
                </tr>
                <td><form:requiredfieldvalidator name='Login.CONTROL_PASSWORD_VALIDATOR%>'
                                                controltovalidate='Login.CONTROL_PASSWORD%>'
                                                nlsid='Login.MSG_PASSWORD_REQUIRED%>' indicator=""/></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</html>

