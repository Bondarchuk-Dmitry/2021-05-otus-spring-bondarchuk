const path = require('path');
const merge = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'production',
    plugins: [

    ],
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'js')
    }
});