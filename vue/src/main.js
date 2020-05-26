import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/reset.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEnvelope, faLock } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { ValidationProvider, extend, ValidationObserver, localize } from 'vee-validate'
import { required, between, max, email } from 'vee-validate/dist/rules'
import ja from 'vee-validate/dist/locale/ja.json'
Vue.component('ValidationProvider', ValidationProvider)
Vue.component('ValidationObserver', ValidationObserver)

extend('required', required)
extend('between', between)
extend('max', max)
extend('email', email)

extend('required', {
  ...required,
  message: '{_field_}を入力してください'
})

extend('email', {
  ...email,
  message: 'この{_field_}は有効ではありません'
})

extend('between', {
  ...between,
  message: '{_field_}は{min}から{max}を入力してください'
})

localize('ja', ja)

library.add(faEnvelope, faLock)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
