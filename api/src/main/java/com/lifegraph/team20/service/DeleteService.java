package com.lifegraph.team20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.models.ParentGraph;
import com.lifegraph.team20.repository.DeleteRepository;

@Service
public class DeleteService {

  @Autowired
  private DeleteRepository deleteRepository;

  public ResponseEntity<String> delete(long userId) {

    // user_idからparent_idを探す
    ParentGraph parent = deleteRepository.parentId(userId);
    long parentId = parent.getId();
    // 子テーブルの情報を消す
    deleteRepository.childDelete(parentId);
    // 親テーブルの情報を消す
    deleteRepository.parentDelete(userId);
    return ResponseEntity.ok("OK");
  }

}
