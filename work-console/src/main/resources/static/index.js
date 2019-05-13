var app=new Vue({
    el:"#app",
    data:{
        message:"",
        newitems:{
            name:'',
            age:'18',
            sex:'女'
        },//newitems默认的
        items:[{
            name:'lily',
            age:18,
            sex:'女'
        },{
            name:'lily',
            age:18,
            sex:'女'
        },{
            name:'lily',
            age:18,
            sex:'女'
        },{
            name:'lily',
            age:18,
            sex:'女'
        },{
            name:'lily',
            age:18,
            sex:'女'
        }]
    },
    methods: {
        addPerson: function () {
            this.items.push(this.newitems)
            this.newitems = {name: '', age: '18', sex: '女'}
        },//添加元素
        deletePerson: function (index) {
            // 删一个数组元素
            this.items.splice(index, 1);
        }
    }
})