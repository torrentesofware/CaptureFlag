<template>
  <div id="app">
    <Banner/>
    <Menu/>
     <router-view/>
  </div>
</template>

<style>
</style>
<script>
// @ is an alias to /src
import Menu from '@/components/Menu.vue'
import Banner from '@/components/Banner.vue'

export default {
  name: 'Home',
  components: {
    Menu,
    Banner
  }
}
</script>
