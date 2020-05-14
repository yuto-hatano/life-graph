//package com.lifegraph.team20.auth;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class ApiGraphSearchController {
//
//  @Autowired
//  ApiGraphSeachService zpcService;
//
//  /**
//   * ユーザーID入力フォーム
//   * @return "GraphID"
//   */
//  @RequestMapping("/id")
//  public String idForm(HttpSession session, Model model) {
//    return "id";
//  }
//
//  /**
//   * グラフ情報表示
//   * @return "id-confirm"
//   */
//  @RequestMapping(value="/id/confirm", method=RequestMethod.POST)
//  public String idConfirm(HttpSession session, Model model,
//		  @RequestParam("id") long id){
//
//   // 一応必須チェックのみ 数字・桁数チェックは省略
//   // nullまたは空文字の場合、入力フォームにエラーメッセージを表示
//	  if (id == null || id.equals("")) {
//		  model.addAttribute("errorMessage", "IDを入力してください。");
//		  return idForm(session, model);
//		  }
//
//	  // グラフ検索APIサービス呼び出し
//	  ApiGraphSeachDto GraphSeachDto = zpcService.service(id);
//      // thymeleafでリストを展開して表示する
//      model.addAttribute("idList", ApiGraphSeachDto.getResults());
//      return "id-confirm";
//  }
//}
