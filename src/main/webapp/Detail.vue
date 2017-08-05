<template>
  <div>
    <h2>{{ detail.title }}</h2>
    <article v-html="detail.content"></article>
  </div>
</template>
<script>
  import { mapRules, mapModules } from 'vuet'

  export default {
    mixins: [
      mapRules({ route: 'cnode/detail' }), // 使用route来做完更新的规则
      mapModules({ detail: 'cnode/detail' })
    ]
  }
</script>