name: Build and Deploy Docker Image

on:
  push:
    branches:
      - main  # Trigger workflow on pushes to the main branch
      - master  # You can also keep master in case you want this workflow triggered for both branches

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    # Step 1: Checkout the repository (checkout main branch to get the Dockerfile)
    - name: Checkout main branch code
      uses: actions/checkout@v3
      with:
        ref: main  # Ensure you're checking out the main branch to use its Dockerfile

    # Step 2: Debug - Validate Dockerfile existence in the main branch
    - name: Validate Dockerfile existence
      run: |
        echo "Checking if Dockerfile exists in the main branch..."
        ls -la  # List files to check if Dockerfile is found
        if [ -f Dockerfile ]; then 
          echo "Dockerfile found in the main branch!";
        else 
          echo "Dockerfile not found in the main branch! Exiting...";
          exit 1;  # Fail the workflow if Dockerfile is missing
        fi

    # Step 3: Set up Docker Buildx for multi-platform builds
    - name: Set up Docker Buildx
      uses: docker/setup-buildx-action@v2

    # Step 4: Log in to DockerHub (use GitHub secrets for DockerHub credentials)
    - name: Log in to DockerHub
      uses: docker/login-action@v2
      with:
        username: ${{ secrets.DOCKER_USERNAME }}
        password: ${{ secrets.DOCKER_PASSWORD }}

    # Step 5: Build and Push Docker Image using Dockerfile from main branch
    - name: Build and Push Docker Image
      uses: docker/build-push-action@v4
      with:
        context: .  # Use the root directory of the repository as the build context
        file: ./Dockerfile  # Specify the Dockerfile in the root of the main branch
        push: true  # Push the image to DockerHub after building
        tags: ${{ secrets.DOCKER_USERNAME }}/my-java-app:latest  # Tag the image
        cache-from: type=registry,ref=${{ secrets.DOCKER_USERNAME }}/my-java-app:latest  # Cache for faster builds
        cache-to: type=registry,ref=${{ secrets.DOCKER_USERNAME }}/my-java-app:latest,mode=max  # Enable caching

    # Optional: Tag the Docker image with commit SHA for unique image versions
    - name: Tag Docker Image with Commit SHA
      run: |
        docker tag ${{ secrets.DOCKER_USERNAME }}/my-java-app:latest ${{ secrets.DOCKER_USERNAME }}/my-java-app:${{ github.sha }}
        docker push ${{ secrets.DOCKER_USERNAME }}/my-java-app:${{ github.sha }}  # Push the commit-specific tag
