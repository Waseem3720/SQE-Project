name: Build and Deploy Docker Image

on:
  push:
    branches:
      - main  # Trigger workflow on pushes to main branch
      - master

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    # Step 1: Checkout the repository
    - name: Checkout code
      uses: actions/checkout@v3

    # Step 2: Debug - Validate Dockerfile existence
    - name: Validate Dockerfile existence
      run: |
        echo "Checking if Dockerfile exists in main branch..."
        ls -la
        if [ -f Dockerfile ]; then 
          echo "Dockerfile found in the main branch!";
        else 
          echo "Dockerfile not found in the main branch! Exiting...";
          exit 1;
        fi

    # Step 3: Set up Docker Buildx
    - name: Set up Docker Buildx
      uses: docker/setup-buildx-action@v2

    # Step 4: Log in to DockerHub
    - name: Log in to DockerHub
      uses: docker/login-action@v2
      with:
        username: ${{ secrets.DOCKER_USERNAME }}
        password: ${{ secrets.DOCKER_PASSWORD }}

    # Step 5: Build and Push Docker Image
    - name: Build and Push Docker Image
      uses: docker/build-push-action@v4
      with:
        context: .  # Root directory of the repository
        file: ./Dockerfile  # Path to Dockerfile in main branch root
        push: true
        tags: ${{ secrets.DOCKER_USERNAME }}/my-java-app:latest
        cache-from: type=registry,ref=${{ secrets.DOCKER_USERNAME }}/my-java-app:latest
        cache-to: type=registry,ref=${{ secrets.DOCKER_USERNAME }}/my-java-app:latest,mode=max

    # Step 6: Optional - Tag the image with commit SHA
    - name: Tag Docker Image with Commit SHA
      run: |
        docker tag ${{ secrets.DOCKER_USERNAME }}/my-java-app:latest ${{ secrets.DOCKER_USERNAME }}/my-java-app:${{ github.sha }}
        docker push ${{ secrets.DOCKER_USERNAME }}/my-java-app:${{ github.sha }}
