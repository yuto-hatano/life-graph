package com.lifegraph.team20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lifegraph.team20.models.Account;
import com.lifegraph.team20.repository.AccountRepository;

@RestController
public class AccountController {
  // 自動で初期化されるフィールドを示している。
  @Autowired
  private AccountRepository accountRepository;

  //  クライアントからのリクエストに対してメソッドやハンドラをマッピングします。
  //  一般的には特定のURLリクエストに対してマッピングを行うが、
  //  属性を使えばGET や POST といったメソッドなどで条件付けすることもできる。
  //  valueのURLがきたらGETメソッドを返す。
  @GetMapping(value = "/accounts/{id}")
  //  HTTP ステータスやコンテンツタイプ以外のレスポンスヘッダを指定したい場合は、戻り値を ResponseEntity<T> にする。
  //  ResponseEntity はボディ、ヘッダ、ステータスを持つクラスで、型 T にはボディの型を指定。
  //  accountというタイプのボディを指定している

  public ResponseEntity<Account> account(@PathVariable("id") Integer id) {
    Account account = accountRepository.sellectAccount(id);
    return ResponseEntity.ok(account);
  }
}
