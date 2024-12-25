# Use Node.js as the build environment
FROM node:18 AS build

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy Angular app source code
COPY . .

# Build the Angular app
RUN npm run build --prod

# Use Nginx to serve the Angular app
FROM nginx:alpine
COPY --from=build /app/dist/your-angular-app /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
