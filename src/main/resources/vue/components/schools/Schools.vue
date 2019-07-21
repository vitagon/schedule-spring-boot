<template>
    <b-container class="pt-5">
        <schools-list></schools-list>
        <edit-school></edit-school>
        <edit-school-translation></edit-school-translation>
    </b-container>
</template>

<script>
import axios from 'axios'
import SchoolsList from 'components/schools/SchoolsList.vue'
import EditSchool from 'components/schools/EditSchool.vue'
import EditSchoolTranslation from 'components/schools/EditSchoolTranslation.vue'
import EventBus from 'EventBus.js'

export default {
    components: {SchoolsList, EditSchool, EditSchoolTranslation},
    created() {
        axios.get('https://localhost:8081/api/locales')
            .then(response => {
                EventBus.$emit('gotLocales', response.data);
            })
    }
}
</script>

