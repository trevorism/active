import { mount } from "@vue/test-utils";
import App from "./src/App.vue";

import { describe, it, expect, vi } from "vitest";
import axios from 'axios'

vi.mock('axios')

describe("App.vue", () => {

    axios.get.mockResolvedValue({ data: [] })

    it("should render the menu bar", () => {
        const wrapper = mount(App);

        expect(wrapper.text()).contains("Apps");
        expect(wrapper.text()).contains("Articles");
        expect(wrapper.text()).contains("Contact");
    });

    it("contains headers for each section", () => {
        const wrapper = mount(App);

        expect(wrapper.text()).contains("Active Trevorism Components");
        expect(wrapper.text()).contains("Active Web Applications");
        expect(wrapper.text()).contains("Active Libraries");

    });

});