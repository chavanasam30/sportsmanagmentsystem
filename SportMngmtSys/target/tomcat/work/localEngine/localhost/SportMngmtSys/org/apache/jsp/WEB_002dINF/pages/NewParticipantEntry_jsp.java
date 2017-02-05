package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class NewParticipantEntry_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("\n");
      out.write("<title>New Participant Entry</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<form action=\"/newParticipantEntry\" >\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t\t<div><h3>PERSONAL INFORMATION</h3></div>\n");
      out.write("\t\t\t\t\t<table>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"labels\">FIRST NAME<span class=\"astrik\">*</span> :</div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"labels\">MIDDLE NAME<span class=\"astrik\">*</span> :</div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"labels\">LAST NAME<span class=\"astrik\">*</span> :</div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\t\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div><input type=\"text\" name=\"txtFName\" id=\"txtFName\"/></div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div><input type=\"text\" name=\"txtMName\" id=\"txtMName\"/></div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<div><input type=\"text\" name=\"txtLName\" id=\"txtLName\"/></div>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr>\t\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">DATE OF BIRTH<span class=\"astrik\">*</span> :</div></td>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">AGE :</div></td>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">GENDER<span class=\"astrik\">*</span> :</div></td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" class=\"dateTimePicker1\" name=\"txtDateOfBirth\" id=\"txtDateOfBirth\" /></td>\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"txtAge\" id=\"txtAge\" disabled=\"disabled\"/></td>\n");
      out.write("\t\t\t\t\t\t\t<td>\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"dropdown-menu\" id=\"selectGender\" name=\"selectGender\" >\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"G\">Girl</option>\n");
      out.write("\t\t\t\t\t\t\t\t\t<option value=\"B\">Boy</option>\n");
      out.write("\t\t\t\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t</tr> \n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">EMAIL ID : </div></td>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">PHONE<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t\t\t\t<td><div class=\"labels\">EMERGENCY PHONE<span class=\"astrik\">*</span>: </div></td>                                              \n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"txtEmailId\" id=\"txtEmailId\"/></td>\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"txtPhone\" id=\"txtPhone\"/></td>\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"txtEmerPhone\" id=\"txtEmerPhone\"/></td>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t </div>\t\t\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t<div><h3>ADDRESS</h3></div>\n");
      out.write("\t\t\t<table>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">ADDRESS LINE 1<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">ADDRESS LINE 2: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtAdd1\" id=\"txtAdd1\"/></td>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtAdd2\" id=\"txtAdd2\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">CITY<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">STATE<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtCity\" id=\"txtCity\"/></td>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtState\" id=\"txtState\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">PINCODE<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtPincode\" id=\"txtPincode\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t<div><h3>SCHOOL ADDRESS</h3></div>\n");
      out.write("\t\t\t<table>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">SCHOOL ADDRESS LINE 1<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">SCHOOL ADDRESS LINE 2: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtSchAdd1\" id=\"txtSchAdd1\"/></td>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtSchAdd2\" id=\"txtSchAdd2\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">SCHOOL CITY<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">SCHOOL STATE<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtSchCity\" id=\"txtSchCity\"/></td>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtSchState\" id=\"txtSchState\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">SCHOOL PINCODE<span class=\"astrik\">*</span>: </div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtSchPincode\" id=\"txtSchPincode\"/></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t<table>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td><div class=\"labels\">INSERT USER NAME: </div></td>\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"txtUserName\" id=\"txtUserName\"/></td>\n");
      out.write("\t\t\t\t\t<td><div class=\"buttondiv\"><input type=\"button\" value=\"Get Games\" id=\"gameBtn\" style=\"float: right;\"></div></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<div class=\"hiddenDiv\" id=\"gameDiv\">\n");
      out.write("\t\t\t<div class=\"container\">\n");
      out.write("\t\t\t\t<table>\n");
      out.write("\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<td><div class=\"labels\">SELECT GAME NAMES YOU WANT TO PARTICIPATE: </div></td> \n");
      out.write("\t\t\t\t\t\t<td><div><select id=\"gameDropDown\"></select></div></td>\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t<td><div class=\"buttondiv\"><input type=\"button\" value=\"Add\" id=\"addBtn\" style=\"float: right;\"></div></td>\t\n");
      out.write("\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t\t</div>\t\n");
      out.write("\t\t</div>\n");
      out.write("\t<h2>Name : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</h2>\n");
      out.write("\t</form>\n");
      out.write("\t</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
