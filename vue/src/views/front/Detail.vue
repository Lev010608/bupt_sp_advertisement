<script setup>

</script>

<template>
  <div>
    <div>
      <div class="carousel-image-container">
        <el-carousel height="300px">
          <div class="overlay">
            <div class="overlay-content">
              <h2>Welcome to my website</h2>
              <p>Discover amazing content</p>
            </div>
          </div>
          <el-carousel-item v-for="item in carouselData" :key="item">
            <img :src="item" class="carousel-image">
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>