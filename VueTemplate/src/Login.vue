<template>
  <div id="component-login">
    <a-row type="flex" justify="center">
      <a-col :span="12" :style="{width:'450px',height:'450px'}">
        <a-space>
          <a-form-model>
            <a-input type="user" v-model="username" placeholder="用户名">
              <a-icon slot="prefix" type="user"/>
            </a-input>
          </a-form-model>
          <a-form-model>
            <a-input type="password" v-model="password" placeholder="密码">
              <a-icon slot="prefix" type="lock"/>
            </a-input>
          </a-form-model>
          <a-button type="primary" @click="login">Log in</a-button>
        </a-space>
      </a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: ''
    }
  },
  methods: {
    login() {
      const _this = this
      this.$axios({
        url: 'http://localhost:80/login',
        params: {
          'username': _this.username,
          'password': _this.password
        }
      }).then(res => {
        console.log(res)
        if (res.data.code !== 500) {
          // 将获取到的两个token使用&符合连接起来
          sessionStorage.setItem('token', res.data.app_id + '&' + res.data.auth_id)
          this.$router.push({path: '/manager'})
        } else {
          sessionStorage.clear()
          alert("用户名或密码错误")
        }
      }).then(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
#component-login {
  margin: 25px;
}
</style>