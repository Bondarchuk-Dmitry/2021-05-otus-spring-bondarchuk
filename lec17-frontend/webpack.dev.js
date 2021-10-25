const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    devtool: 'source-map',
    devServer: {
        proxy: [{
            context: ['/auth', '/api'],
            target: 'http://localhost:8080',
        }],
        contentBase: './dist',
        compress: true,
        port: 8000,
        stats: 'errors-only',
        clientLogLevel: 'error',
    }
});