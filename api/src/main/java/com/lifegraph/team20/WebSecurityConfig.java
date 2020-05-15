package com.lifegraph.team20;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) throws Exception {
    //    各静的ファイルを認証不要にする
    web.ignoring().antMatchers("/img/**", "/css/**", "/js/**", "/webjars/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        //    ログイン画面
        .antMatchers("/Login")
        //    全ユーザーアクセス可
        .permitAll()
        //    管理画面
        .antMatchers("/auth/login**")
        //    ADMIN権限者のみアクセス可能
        .hasRole("ADMIN")
        //    全てのURLリクエストは認証されているユーザーしかアクセスできない
        .anyRequest().authenticated();
    http.formLogin()
        //    ログイン画面のURL
        .loginPage("/Login")
        //    ログイン処理をするURL→API？
        .loginProcessingUrl("/signin")
        .usernameParameter("loginID")
        .passwordParameter("password")
        //    ログイン成功時に遷移する画面のURL
        .defaultSuccessUrl("/Top", false)
        .permitAll()
        .and()
        .logout()
        //    ログアウトのURL
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        //    ログアウトにアクセスが来たらセッションIDを削除してログイン画面に遷移
        .logoutSuccessUrl("/Login")
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .permitAll();
  }
}
//  databaseとの関連付け※データベース後に整理
//  @Autowired
//  public void configure(AuthenticationManagerBuilder auth) throws Exception{
//    auth
//        .jdbcAuthentication()
//          .dataSource(dataSource)
//          .usersByUsernameQuery(
//              "select adress as loginId,password,")
//
//}}
