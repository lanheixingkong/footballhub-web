<template>
  <div>
    <header>
      <span v-for="(item, $index) in tabs" :key="item.value">
        <router-link :to="{ name: 'index', query: { tab: item.value } }">
          {{ item.label }}
        </router-link>
      </span>
    </header>
    <ul>
      <li v-for="(item, $index) in list.list" :key="item.id">
        <router-link :to="{ name: 'detail', params: { id: item.id } }">
          {{ item.title }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
<script>
  import { mapRules, mapModules } from 'vuet'

  export default {
    mixins: [
      mapRules({ route: 'cnode/list' }), // 使用route来做完更新的规则
      mapModules({ list: 'cnode/list' })
    ],
    data () {
      return {
        // 其实这里的数据是写死的数据，大家可以放到local插件中使用的。
        // 具体怎么用，大家自己发挥一下想象力咯
        tabs: [
          {
            label: '全部',
            value: 'all'
          },
          {
            label: '精华',
            value: 'good'
          },
          {
            label: '分享',
            value: 'share'
          },
          {
            label: '问答',
            value: 'ask'
          },
          {
            label: '招聘',
            value: 'job'
          }
        ]
      }
    }
  }
</script>