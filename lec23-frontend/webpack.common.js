const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin'); //installed via npm

module.exports = {
    output: {
        path: path.resolve(__dirname, "dist"),
    },
    entry: path.join(__dirname, 'src', 'main', 'js', 'main.js'),
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },

            {
                test: /\.(c|sc)ss$/,
                use: [
                    {
                        loader: "style-loader"
                    },
                    {
                        loader: "css-loader"
                    },
                    {
                        loader: "sass-loader"
                    }

                ]
            },
            {
                test:/\.(eot|svg|ttf|woff|woff2)$/,
                loader:'url-loader'
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, "public", "index.html")
        }),
        new VueLoaderPlugin(),
    ],
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'js'),
            path.join(__dirname, 'node_modules')
        ],
    }
}