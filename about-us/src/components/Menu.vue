<template>
<div>
    <header class="d-flex align-items-center">
    <div class="container">

      <!-- The main logo is shown in mobile version only. The centered nav-logo in nav menu is displayed in desktop view  -->
      <div class="logo d-block d-lg-none">
        <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>
      </div>

      <nav class="nav-menu d-none d-lg-block">
        <ul class="nav-inner">
          <li class="active"><router-link to="/">Home</router-link></li>
          <li class="drop-down"><a href="">About</a>
            <ul>
              <li><router-link to="/about">About</router-link></li>
              <li><a href="#team">Team</a></li>

              <li class="drop-down"><a href="#">Deep Drop</a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li><a href="#services">Services</a></li>

          <li class="nav-logo"><a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a></li>

          <li><a href="#portfolio">Portfolio</a></li>
          <li><a href="#pricing">Pricing</a></li>
          <li><a href="#contact">Contact</a></li>

        </ul>
      </nav><!-- .nav-menu -->

    </div>
  </header><!-- End Header -->
</div>
</template>
<style>

</style>
<script>
export default {
  name: 'Menu',
}
</script>