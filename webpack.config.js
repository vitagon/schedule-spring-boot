const path = require('path')
const fs = require('fs')
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
    mode: 'development',
    devtool: 'source-map',
    entry: [
    	path.join(__dirname, 'src', 'main', 'resources', 'vue', 'main.js'),
	],
    output: {
        filename: 'adminpanel.js',
        path: path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'js')
    },
    devServer: {
        contentBase: ['./dist','./src/main/resources/templates'],
        compress: true,
        port: 3000,
        allowedHosts: [
            'localhost:8081'
        ],
        stats: 'errors-only',
        clientLogLevel: 'error',
        // https: true,
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Headers": "*"
        },
        hot: false,
        inline: false
    },
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
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.scss$/,
                use: [
                    'vue-style-loader',
                    'css-loader',
                    'sass-loader'
                ]
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ],
    resolve: {
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'vue'),
            path.join(__dirname, 'node_modules')
        ]
    }
}