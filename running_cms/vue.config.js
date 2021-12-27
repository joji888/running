module.exports = {
    publicPath:'./',
    configureWebpack: {
        devServer: {
            proxy: {
                '/api': {
                    target: 'http://localhost:5056',
                    changeOrigin: true,
                    pathRewrite: {
                        '^/api': ''
                    }
                }
            }
        }
    }
}
