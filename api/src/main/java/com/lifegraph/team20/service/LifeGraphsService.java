package com.lifegraph.team20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.models.LifeGraphData;
import com.lifegraph.team20.models.ParentGraph;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.DeleteRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;

@Service
public class LifeGraphsService {

  @Autowired
  private ParentGraphRepository parentRepository;
  @Autowired
  private ChildGraphRepository childRepository;
  @Autowired
  private DeleteRepository deleteRepository;

  /**
   * 登録・編集のメイン処理
   *
   * @param data 登録・編集リクエスト
   */
  public void resiter(LifeGraphData data) {
    // parent table
    //dataに入っているuser_idのみ取り出す
    //それを引数としてregisterParentを呼び出す

    long parentId = resiterParent(data.getUserId());
    // child table
    registerChild(parentId, data);
  }

  /**
   * 親グラフの登録・更新を行う
   *
   * @param userId ユーザーID
   * @return 親グラフID
   */
  private long resiterParent(long userId) {
    // exists parent by userId
    long parentId;
    Optional<ParentGraph> parent = parentRepository.findByUserId(userId);
    //    if (parentRepository.existsByUserId(userId)) {
    if (parent.isPresent()) {
      //プレゼントがある→データがある
      // update
      parentId = parent.get().getId(); //parentIdには親グラフのidをつめる。idとparent_idはつながっているので。
      // 更新日時を登録
      parentRepository.update(userId);
    } else {
      //プレゼントが無い→データが無い→レコードの追加
      // insert
      parentId = parentRepository.insert(userId);
    }
    return parentId;
  }

  private void registerChild(Long parentId, LifeGraphData data) {
    // exists parent by userId
    if (childRepository.existsByUserIdAndAge(parentId, data.getAge())) {
      // update
      childRepository.updateChild(data.getId(), data.getAge(), data.getScore(), data.getComment());
    } else {
      // insert
      childRepository.addChild(parentId, data.getAge(), data.getScore(), data.getComment());
    }
  }

  public void clear(LifeGraphData data) {
    long id = data.getId();
    deleteRepository.clear(id);
  }

  //-----ここから削除API(全データ)-----
  public void delete(LifeGraphData data) {
    long userId = data.getUserId();
    // user_idからparent_idを探す
    ParentGraph parent = deleteRepository.parentId(userId);
    long parentId = parent.getId();
    // 子テーブルの情報を消す
    deleteRepository.childDelete(parentId);
    // 親テーブルの情報を消す
    deleteRepository.parentDelete(userId);
  }
}
