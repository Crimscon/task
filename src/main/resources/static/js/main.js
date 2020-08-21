function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var authorApi = Vue.resource('/author{/id}');

Vue.component('author-form', {
    props: ['authors', 'authorAttr'],
    data: function() {
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        authorAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="name" placeholder="Write name" v-model="name" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var author = { name: this.name };

            if (this.id) {
                authorApi.update({id: this.id}, author).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.authors, data.id);
                        this.authors.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                authorApi.save({}, author).then(result =>
                    result.json().then(data => {
                        this.authors.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('author-row', {
    props: ['author', 'editMethod', 'authors'],
    template: '<div>' +
        '<i>({{ author.id }})</i> {{ author.name }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.author);
        },
        del: function() {
            authorApi.remove({id: this.author.id}).then(result => {
                if (result.ok) {
                    this.authors.splice(this.authors.indexOf(this.author), 1)
                }
            })
        }
    }
});

Vue.component('authors-list', {
    props: ['authors'],
    data: function() {
        return {
            author: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<author-form :authors="authors" :authorAttr="author" />' +
        '<author-row v-for="author in authors" :key="author.id" :author="author" ' +
        ':editMethod="editMethod" :authors="authors" />' +
        '</div>',
    methods: {
        editMethod: function(author) {
            this.author = author;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<authors-list :authors="authors" />',
    data: {
        authors: authorsFromModel
    },
    created: function() {
        authorApi.get().then(result =>
            result.json().then(data =>
                data.forEach(author => this.authors.push(author))
            )
        )
    }
});