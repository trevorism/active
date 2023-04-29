import { mount, flushPromises } from "@vue/test-utils";
import Webapps from "/src/components/Webapps.vue";

import { describe, it, expect, vi } from "vitest";
import axios from 'axios'


vi.mock('axios')

describe("Webapps.vue", () => {

    it("should render the headers", () => {
        axios.get.mockResolvedValue({ data: [] })
        const wrapper = mount(Webapps);

        expect(wrapper.text()).contains("Name");
        expect(wrapper.text()).contains("Deploy URL");
        expect(wrapper.text()).contains("Github");
        expect(wrapper.text()).contains("Deploy Status");
    });

    it("should render the webapp", async () => {
        axios.get.mockImplementation((url) => {
            switch (url) {
                case 'api/active/service':
                    return Promise.resolve({data: [{name: 'test', url:'', github: 'https://github.com/trevorism/test', statusUrl:'x.svg'}]})
                case 'api/active/service/test':
                    return Promise.resolve({data: {name:"test", dns: "unit.test"}});
                default:
                    return Promise.reject(new Error('not found'))
            }
        })

        const wrapper = mount(Webapps);
        await flushPromises();

        expect(wrapper.text()).contains("test");
        expect(wrapper.find("a").attributes("href")).contains("https://test.unit.test.trevorism.com");
        expect(wrapper.findAll("a").at(1).attributes("href")).contains("https://github.com/trevorism/test");
        expect(wrapper.find("img").attributes("src")).contains("x.svg");

    });

});