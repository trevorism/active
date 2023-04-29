import { mount, flushPromises } from "@vue/test-utils";
import Libraries from "/src/components/Libraries.vue";

import { describe, it, expect, vi } from "vitest";
import axios from 'axios'
import {nextTick} from "vue";

vi.mock('axios')

describe("Libraries.vue", () => {



    it("should render the headers", () => {
        axios.get.mockResolvedValue({ data: [] })
        const wrapper = mount(Libraries);

        expect(wrapper.text()).contains("Name");
        expect(wrapper.text()).contains("Version");
        expect(wrapper.text()).contains("Github");
        expect(wrapper.text()).contains("Deploy Status");
    });

    it("should render the library", async () => {
        axios.get.mockImplementation((url) => {
            switch (url) {
                case 'api/active/library':
                    return Promise.resolve({data: [{name: 'test', github: 'https://github.com/trevorism/test', statusUrl:'x.svg'}]})
                case 'api/active/library/test/version':
                    return Promise.resolve({data: "20.0.0"})
                default:
                    return Promise.reject(new Error('not found'))
            }
        })

        const wrapper = mount(Libraries);
        await flushPromises();

        expect(wrapper.text()).contains("test");
        expect(wrapper.text()).contains("20.0.0");
        expect(wrapper.find("a").attributes("href")).contains("https://github.com/trevorism/test");
        expect(wrapper.find("img").attributes("src")).contains("x.svg");

    });

});