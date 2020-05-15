//package com.lifegraph.team20.controllers;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
////モダンブラウザでは別のサイトにアクセスしようとすると、
////アクセス許可の確認をするようになっている。
////これはサイトをまたいだアクセス、Cross-Origin Resource Sharing (CORS)と言う.
////その対策にCrossOriginアノテーションをつける
//@CrossOrigin(origins = "*", maxAge = 3600)
////RestControllerではViewに遷移せず、メソッドの戻り値がそのままレスポンスコンテンツになる。
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//  @Autowired
//  AuthenticationManager authenticationManager;
//
//  @Autowired
//  UserRepository userRepository;
//
//  @Autowired
//  RoleRepositoiry roleRepository;
//
//  @Autowired
//  PasswordEncoder encoder;
//
//  @Autowired
//  JwtUtils jwtUtils;
//
//  @PostMapping("/signin")
//        public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginRequest loginRequest){
//
//               Authentication authentication = authenticationManager.authenticate(
//                               new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
//
//                               SecurityContextHolder.getContext().setAuthentication(authentication);
//                               String jwt = jwtUtils.generateJwtToken(authentication);
//
//                               UserDetailImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//                               List<String>roles = userDetails.getAuthorities().stream()
//                                   .map(item -> item,getAuthority())
//                                   .collect(collectors.toList());
//
//                               return ResponseEntity.ok(new JwtResponse(jwt,
//                                   userDetails.getId(),
//                                   userDetails.getUsername(),
//                                   userDetails.getEmail(),
//                                   roles));
//        }
//
//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Vailid @RequestBody SignupRequest signUpRequest){
//          if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                   .badRequest()
//                   body(new MessageResponse("Error: Username is alredy taken!"));
//          }
//
//          if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already in use!"));
//          }
//
//// ユーザーアカウント登録
//          User user = new User(signUpRequest.getUsername(),
//              signUpRequest.getEmail(),
//              encoder.encode(signUpRequest.getPassword()));
//
//          Set<>
//  }
//
//  @RequestMapping(value = "/signin", method = RequestMethod.GET)
//  public ResponseEntity<String> login() {
//    return ResponseEntity.ok("OK");
//  }
//}
