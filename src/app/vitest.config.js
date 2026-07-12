import { fileURLToPath } from 'node:url'
import { mergeConfig } from 'vite'
import { configDefaults, defineConfig } from 'vitest/config'
import TrevorismTestResultReporter from '@trevorism/vitest-test-result-events'
import viteConfig from './vite.config'
import { resolve } from 'path'

export default mergeConfig(
  viteConfig,
  defineConfig({
    test: {
      environment: 'jsdom',
      exclude: [...configDefaults.exclude, 'e2e/*'],
      root: fileURLToPath(new URL('./', import.meta.url)),
      setupFiles: resolve("test/setup.js"),
      server: {
        deps: {
          inline: ["@trevorism/ui-header-bar"]
        }
      },
      reporters: ['default', new TrevorismTestResultReporter('active')]
    }
  })
)
